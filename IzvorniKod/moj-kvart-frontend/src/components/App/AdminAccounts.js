import AdminHeader from './AdminHeader';
import React, {Component} from 'react';
import Card from "./Card";
import './AdminAccounts.css';
import DropdownMenu from "./DropdownMenu";
import CustomButton from './Button';
import Dialog from "@material-ui/core/Dialog/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent/DialogContent";
import TextField from "@material-ui/core/TextField/TextField";
import DialogActions from "@material-ui/core/DialogActions/DialogActions";
import Button from "@material-ui/core/es/Button/Button";

class AdminAccounts extends Component {

    state = {
        councilorUsername: '',
        councilorPassword: '',
        councilorFirstName: '',
        councilorLastName: '',
        councilorEmail: '',
        councilorStreet: '',
        moderatorUsername: '',
        moderatorPassword: '',
        moderatorFirstName: '',
        moderatorLastName: '',
        moderatorEmail: '',
        moderatorStreet: '',
        adminUsername: '',
        adminPassword: '',
        errorCouncilor: '',
        errorModerator: '',
        errorAdmin: '',
        streets: [],
        openCouncilor: false,
        openModerator: false,
        openAdmin: false
    };

    componentDidMount() {
        fetch('/streets')
            .then(response => {
                return response.json();
            }).then(streets => {
            this.setState({streets: streets})
        })
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    handleClickOpenCouncilor = () => {
        this.setState({openCouncilor: true});
    };

    handleCloseCouncilor = () => {
        this.setState({openCouncilor: false, errorCouncilor: ''});
    };

    handleClickOpenModerator = () => {
        this.setState({openModerator: true});
    };

    handleCloseModerator = () => {
        this.setState({openModerator: false, errorModerator: ''});
    };

    handleClickOpenAdmin = () => {
        this.setState({openAdmin: true});
    };

    handleCloseAdmin = () => {
        this.setState({openAdmin: false, errorAdmin: ''});
    };

    onSubmitCouncilor = (e) => {
        e.preventDefault();

        let item = document.getElementById("councilorStreet");
        let selectedText = item.options[item.selectedIndex].text
        this.state.councilorStreet = selectedText
        //this.setState({councilorStreet: selectedText})

        const data = {
            username: this.state.councilorUsername,
            password: this.state.councilorPassword,
            firstName: this.state.councilorFirstName,
            lastName: this.state.councilorLastName,
            email: this.state.councilorEmail,
            street: this.state.councilorStreet,
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/users/councilors', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400) {
                    this.setState({errorCouncilor: ''});
                    alert("Councilor account created");
                    this.handleCloseCouncilor();
                    this.setState({councilorUsername: ''});
                    this.setState({councilorPassword: ''});
                    this.setState({councilorFirstName: ''});
                    this.setState({councilorEmail: ''});
                }
                else {
                    this.setState({errorCouncilor: response.message});
                }
            })
    };

    onSubmitModerator = (e) => {
        e.preventDefault();

        let item = document.getElementById("moderatorStreet");
        let selectedText = item.options[item.selectedIndex].text
        this.state.moderatorStreet = selectedText;
        //this.setState({ moderatorStreet: selectedText });

        const data = {
            username: this.state.moderatorUsername,
            password: this.state.moderatorPassword,
            firstName: this.state.moderatorFirstName,
            lastName: this.state.moderatorLastName,
            email: this.state.moderatorEmail,
            street: this.state.moderatorStreet,
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/users/moderators', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400) {
                    this.setState({errorModerator: ''});
                    alert("Moderator account created");
                    this.handleCloseModerator();
                    this.setState({moderatorUsername: ''});
                    this.setState({moderatorPassword: ''});
                    this.setState({moderatorFirstName: ''});
                    this.setState({moderatorEmail: ''});
                }
                else {
                    this.setState({errorModerator: response.message});
                }
            })
    };

    onSubmitAdmin = (e) => {
        e.preventDefault();

        const data = {
            username: this.state.adminUsername,
            password: this.state.adminPassword
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/users/admins', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400) {
                    this.setState({errorAdmin: ''});
                    alert("Admin account created");
                    this.handleCloseAdmin();
                    this.setState({adminUsername: ''});
                    this.setState({adminPassword: ''});
                }
                else {
                    this.setState({errorAdmin: response.message});
                }
            })
    };

    render() {
        let streets = this.state.streets;
        let optionItems = streets.map(street => <option key={street.id}>{street.name}</option>);

        return (
            <div>
                <AdminHeader {...this.props} onLogout={this.props.onLogout}/>
                <div className="CMD">
                    <Card title='Choose account to create'>

                        <div style={{height: 20}}/>

                        <div align="center">
                            <CustomButton onClick={this.handleClickOpenCouncilor}>Create councilor</CustomButton>
                        </div>

                        <div style={{height: 20}}/>

                        <div align="center">
                            <CustomButton onClick={this.handleClickOpenModerator}>Create moderator</CustomButton>
                        </div>

                        <div style={{height: 20}}/>

                        <div align="center">
                            <CustomButton onClick={this.handleClickOpenAdmin}>Create admin</CustomButton></div>

                        <Dialog
                            open={this.state.openCouncilor}
                            onClose={this.handleCloseCouncilor}
                        >
                            <DialogTitle>Create councilor</DialogTitle>
                            <DialogContent>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Username'
                                    fullWidth
                                    name='councilorUsername'
                                    onChange={this.handleChange}
                                    value={this.state.councilorUsername}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Password'
                                    fullWidth
                                    name='councilorPassword'
                                    type='password'
                                    onChange={this.handleChange}
                                    value={this.state.councilorPassword}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='First name'
                                    fullWidth
                                    name='councilorFirstName'
                                    onChange={this.handleChange}
                                    value={this.state.councilorFirstName}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Last name'
                                    fullWidth
                                    name='councilorLastName'
                                    onChange={this.handleChange}
                                    value={this.state.councilorLastName}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Email'
                                    fullWidth
                                    name='councilorEmail'
                                    onChange={this.handleChange}
                                    value={this.state.councilorEmail}
                                />

                                <div style={{height: 10}}/>
                                <label>Street&nbsp;&nbsp;</label>
                                <DropdownMenu id='councilorStreet'>
                                    {optionItems}
                                </DropdownMenu>
                            </DialogContent>
                            <div className='error'>{this.state.errorCouncilor}</div>
                            <DialogActions>
                                <Button onClick={this.onSubmitCouncilor}>
                                    Add councilor account
                                </Button>
                                <Button onClick={this.handleCloseCouncilor}>
                                    Cancel
                                </Button>
                            </DialogActions>
                        </Dialog>

                        <Dialog
                            open={this.state.openModerator}
                            onClose={this.handleCloseModerator}
                        >
                            <DialogTitle>Create moderator</DialogTitle>
                            <DialogContent>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Username'
                                    fullWidth
                                    name='moderatorUsername'
                                    onChange={this.handleChange}
                                    value={this.state.moderatorUsername}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Password'
                                    fullWidth
                                    name='moderatorPassword'
                                    type='password'
                                    onChange={this.handleChange}
                                    value={this.state.moderatorPassword}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='First name'
                                    fullWidth
                                    name='moderatorFirstName'
                                    onChange={this.handleChange}
                                    value={this.state.moderatorFirstName}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Last name'
                                    fullWidth
                                    name='moderatorLastName'
                                    onChange={this.handleChange}
                                    value={this.state.moderatorLastName}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Email'
                                    fullWidth
                                    name='moderatorEmail'
                                    onChange={this.handleChange}
                                    value={this.state.moderatorEmail}
                                />

                                <div style={{height: 10}}/>
                                <label>Street&nbsp;&nbsp;</label>
                                <DropdownMenu id='moderatorStreet'>
                                    {optionItems}
                                </DropdownMenu>
                            </DialogContent>
                            <div className='error'>{this.state.errorModerator}</div>
                            <DialogActions>
                                <Button onClick={this.onSubmitModerator}>
                                    Add moderator account
                                </Button>
                                <Button onClick={this.handleCloseModerator}>
                                    Cancel
                                </Button>
                            </DialogActions>
                        </Dialog>

                        <Dialog
                            open={this.state.openAdmin}
                            onClose={this.handleCloseAdmin}
                        >
                            <DialogTitle>Create admin</DialogTitle>
                            <DialogContent>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Username'
                                    fullWidth
                                    name='adminUsername'
                                    onChange={this.handleChange}
                                    value={this.state.adminUsername}
                                />

                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Password'
                                    fullWidth
                                    name='adminPassword'
                                    type='password'
                                    onChange={this.handleChange}
                                    value={this.state.adminPassword}
                                />

                            </DialogContent>
                            <div className='error'>{this.state.errorAdmin}</div>
                            <DialogActions>
                                <Button onClick={this.onSubmitAdmin}>
                                    Add admin account
                                </Button>
                                <Button onClick={this.handleCloseAdmin}>
                                    Cancel
                                </Button>
                            </DialogActions>
                        </Dialog>
                    </Card>
                </div>
            </div>
        )
    }
}

export default AdminAccounts;
