import AppHeader from './AppHeader';
import React, {Component} from 'react';
import Card from "./Card";
import './Profile.css'
import CustomButton from './Button';
import DropdownMenu from "./DropdownMenu";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from "@material-ui/core/TextField/TextField";
import TableRow from "@material-ui/core/TableRow/TableRow";
import Table from "@material-ui/core/Table/Table";
import TableCell from "@material-ui/core/TableCell/TableCell";
import TableBody from "@material-ui/core/TableBody/TableBody";
import Button from "@material-ui/core/es/Button/Button";

class Profile extends Component {
    state = {
        user: {
            id: '',
            username: '',
            createdOn: '',
            firstName: '',
            lastName: '',
            email: '',
            street: '',
            role: ''
        },
        username2: '',
        password2: '',
        firstName2: '',
        lastName2: '',
        email2: '',
        street2: '',
        streets: [],
        error: '',
        open: false
    }

    componentDidMount = () => {
        fetch('/user/info')
            .then(data => data.json())
            .then(user => this.setState({user: user}))

        fetch('/streets')
            .then(response => {
                return response.json();
            }).then(streets => {
            this.setState({streets: streets})
        })
    };

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    handleClickOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false, error: ''});
    };

    onSubmit = (e) => {
        e.preventDefault();

        let item = document.getElementById("updateInfo");
        let selectedText = item.options[item.selectedIndex].text
        this.state.street2 = selectedText;
        //this.setState({ street2: selectedText })

        const data = {
            username: this.state.username2,
            password: this.state.password2,
            firstName: this.state.firstName2,
            lastName: this.state.lastName2,
            email: this.state.email2,
            street: this.state.street2,
            //createdOn: new Date()
        }

        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/user/info', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400) {
                    this.setState({error: ''});
                    this.setState({open: false});
                    alert("Information was succesfully updated! Please login again and check updated information!");
                    this.props.history.push('/');
                }
                else {
                    this.setState({error: 'Information update failed: ' + response.message});
                }
            })
    }

    render() {
        let streets = this.state.streets;
        let optionItems = streets.map(street => <option key={street.id}>{street.name}</option>);

        return (
            <div>
                <AppHeader {...this.props} onLogout={this.props.onLogout}/>
                <div className='Profile'>
                    <Card title='About me'>
                        <div className='containter'>
                            <Table>
                                <TableBody>
                                    <TableRow>
                                        <TableCell>
                                            <label>Username</label>
                                        </TableCell>
                                        <TableCell>
                                            <input type="text" value={this.state.user.username}
                                                   readOnly='readOnly'/>
                                        </TableCell>
                                    </TableRow>

                                    <TableRow>
                                        <TableCell>
                                            <label>First name</label>
                                        </TableCell>
                                        <TableCell>
                                            <input type="text" value={this.state.user.firstName}
                                                   readOnly='readOnly'/>
                                        </TableCell>
                                    </TableRow>

                                    <TableRow>
                                        <TableCell>
                                            <label>Last name</label>
                                        </TableCell>
                                        <TableCell>
                                            <input type="text" value={this.state.user.lastName}
                                                   readOnly='readOnly'/>
                                        </TableCell>
                                    </TableRow>

                                    <TableRow>
                                        <TableCell>
                                            <label>Email</label>
                                        </TableCell>
                                        <TableCell>
                                            <input type="text" value={this.state.user.email}
                                                   readOnly='readOnly'/>
                                        </TableCell>
                                    </TableRow>

                                    <TableRow>
                                        <TableCell>
                                            <label>Street name</label>
                                        </TableCell>
                                        <TableCell>
                                            <input type="text" value={this.state.user.street}
                                                   readOnly='readOnly'/>
                                        </TableCell>
                                    </TableRow>

                                    <TableRow>
                                        <TableCell>
                                            <label>Role</label>
                                        </TableCell>
                                        <TableCell>
                                            <input type="text" value={this.state.user.role}
                                                   readOnly='readOnly'/>
                                        </TableCell>
                                    </TableRow>
                                    <TableRow style={{height: 10}}/>
                                </TableBody>
                            </Table>
                            <div align="center">
                                <CustomButton onClick={this.handleClickOpen}>Edit account information</CustomButton>
                            </div>
                        </div>
                    </Card>
                </div>
                <Dialog
                    open={this.state.open}
                    onClose={this.handleClose}
                >
                    <DialogTitle>Enter new information</DialogTitle>
                    <DialogContent>
                        <TextField
                            autoFocus
                            margin="dense"
                            label="Username"
                            fullWidth
                            name='username2'
                            onChange={this.handleChange}
                            value={this.state.username2}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            label="Password"
                            fullWidth
                            name='password2'
                            type='password'
                            onChange={this.handleChange}
                            value={this.state.password2}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            label="First name"
                            fullWidth
                            name='firstName2'
                            onChange={this.handleChange}
                            value={this.state.firstName2}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            label="Last name"
                            fullWidth
                            name='lastName2'
                            onChange={this.handleChange}
                            value={this.state.lastName2}
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            label="Email"
                            fullWidth
                            name='email2'
                            onChange={this.handleChange}
                            value={this.state.email2}
                        />
                        <div style={{height: 10}}/>
                        <label>Street&nbsp;&nbsp;</label>
                        <DropdownMenu id='updateInfo'>
                            {optionItems}
                        </DropdownMenu>
                    </DialogContent>
                    <div className='error'>{this.state.error}</div>
                    <DialogActions>
                        <Button onClick={this.onSubmit}>
                            Change account information
                        </Button>
                        <Button onClick={this.handleClose}>
                            Cancel
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        )
    }
}

export default Profile;
