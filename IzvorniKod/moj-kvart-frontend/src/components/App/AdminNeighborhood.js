import AdminHeader from './AdminHeader';
import React, {Component} from 'react';
import './AdminNeighborhood.css';
import Card from "./Card";
import DropdownMenu from "./DropdownMenu";
import DialogTitle from "@material-ui/core/DialogTitle/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent/DialogContent";
import TextField from "@material-ui/core/TextField/TextField";
import DialogActions from "@material-ui/core/DialogActions/DialogActions";
import Dialog from "@material-ui/core/Dialog/Dialog";
import Button from "@material-ui/core/es/Button/Button";
import CustomButton from './Button';

class AdminNeighborhood extends Component {
    state = {
        neighborhoodName: '',
        neighborhoodNameStreet: '',
        streetName: '',
        nameToDestroy: '',
        errorNeighborhood: '',
        errorStreet: '',
        hoods: [],
        openHood: false,
        openStreet: false,
        openDestroy: false,
        errorDestroy: ''
    };

    componentDidMount() {
        fetch('/hood')
            .then(response => {
                return response.json();
            }).then(hoods => {
            this.setState({hoods: hoods})
        })
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    handleClickOpenHood = () => {
        this.setState({openHood: true});
    };

    handleCloseHood = () => {
        this.setState({openHood: false, errorNeighborhood: ''});
    };

    handleClickOpenStreet = () => {
        this.setState({openStreet: true});
    };

    handleCloseStreet = () => {
        this.setState({openStreet: false, errorStreet: ''});
    };

    handleClickOpenDestroy = () => {
        this.setState({openDestroy: true});
    }

    handleClickCloseDestroy = () => {
        this.setState({openDestroy: false, errorDestroy: ''});
    }

    onSubmitNeighborhood = (e) => {
        e.preventDefault();

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: this.state.neighborhoodName
        };

        return fetch('/hood', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400) {
                    this.setState({errorNeighborhood: ''});
                    alert("Neighborhood created");
                    this.handleCloseHood();
                    this.setState({neighborhoodName: ''});
                    this.componentDidMount();
                }
                else {
                    this.setState({errorNeighborhood: response.message});
                }
            })
    };


    onSubmitStreet = (e) => {
        e.preventDefault();

        let item = document.getElementById("neighborhoodNameStreet");
        let selectedText = item.options[item.selectedIndex].text
        this.state.neighborhoodNameStreet = selectedText;
        //this.setState({ neighborhoodNameStreet: selectedText });

        const data = {
            name: this.state.streetName,
            neighborhood: this.state.neighborhoodNameStreet
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/streets', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400) {
                    this.setState({errorStreet: ''});
                    alert("Street created");
                    this.handleCloseStreet();
                    this.setState({neighborhoodNameStreet: ''});
                    this.setState({streetName: ''});
                }
                else {
                    console.log(data.neighborhood)
                    this.setState({errorStreet: response.message});
                }
            })
    };

    onSubmitDestroy = (e) => {
        e.preventDefault();

        let item = document.getElementById("neighborhoodNameDestroy");
        let selectedText = item.options[item.selectedIndex].text

        let i;
        let hoodId;
        for (i = 0; i < this.state.hoods.length; i++) {
            if (selectedText === this.state.hoods[i].name) {
                hoodId = this.state.hoods[i].id;
                break;
            }
        }

        let mapping = '/hood/' + hoodId;

        const options = {
            method: 'DELETE'
        };

        return fetch(mapping, options)
            .then(response => {
                if (response.ok) {
                    this.setState({errorDestroy: ''});
                    alert("Neighborhood deleted!");
                    this.componentDidMount();
                }
                else {
                    this.setState({errorDestroy: response.json().message});
                }
            })
    }

    render() {
        let hoods = this.state.hoods;
        let optionItems = hoods.map(hood => <option key={hood.id}>{hood.name}</option>);

        return (
            <div>
                <AdminHeader {...this.props} onLogout={this.props.onLogout}/>
                <div className="HS">
                    <Card title='Choose an option'>

                        <div style={{height: 20}}/>

                        <div align="center">
                            <CustomButton onClick={this.handleClickOpenHood}>Create neighborhood</CustomButton>
                        </div>

                        <div style={{height: 20}}/>

                        <div align="center">
                            <CustomButton onClick={this.handleClickOpenStreet}>Create street</CustomButton>
                        </div>

                        <div style={{height: 20}}/>

                        <div align="center">
                            <CustomButton onClick={this.handleClickOpenDestroy}>Remove neighborhood</CustomButton>
                        </div>

                        <Dialog
                            open={this.state.openHood}
                            onClose={this.handleCloseHood}
                        >
                            <DialogTitle>Create neighborhood</DialogTitle>
                            <DialogContent>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Neighborhood name'
                                    fullWidth
                                    name='neighborhoodName'
                                    onChange={this.handleChange}
                                    value={this.state.neighborhoodName}
                                />
                            </DialogContent>
                            <div className='error'>{this.state.errorNeighborhood}</div>
                            <DialogActions>
                                <Button onClick={this.onSubmitNeighborhood}>
                                    Add neighborhood
                                </Button>
                                <Button onClick={this.handleCloseHood}>
                                    Cancel
                                </Button>
                            </DialogActions>
                        </Dialog>

                        <Dialog
                            open={this.state.openStreet}
                            onClose={this.handleCloseStreet}
                        >
                            <DialogTitle>Create street</DialogTitle>
                            <DialogContent>
                                <label>Neighborhood where street belongs&nbsp;&nbsp;</label>
                                <DropdownMenu id="neighborhoodNameStreet">
                                    {optionItems}
                                </DropdownMenu>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label='Street name'
                                    fullWidth
                                    name='streetName'
                                    onChange={this.handleChange}
                                    value={this.state.streetName}
                                />
                            </DialogContent>
                            <div className='error'>{this.state.errorStreet}</div>
                            <DialogActions>
                                <Button onClick={this.onSubmitStreet}>
                                    Add street
                                </Button>
                                <Button onClick={this.handleCloseStreet}>
                                    Cancel
                                </Button>
                            </DialogActions>
                        </Dialog>

                        <Dialog
                            open={this.state.openDestroy}
                            onClose={this.handleClickCloseDestroy}
                        >
                            <DialogTitle>Remove neighborhood</DialogTitle>
                            <DialogContent>
                                <label>Choose neighborhood you want to remove&nbsp;&nbsp;</label>
                                <DropdownMenu id="neighborhoodNameDestroy">
                                    {optionItems}
                                </DropdownMenu>
                            </DialogContent>
                            <div className='error'>{this.state.errorDestroy}</div>
                            <DialogActions>
                                <Button onClick={(e) => {
                                    if (window.confirm('Are you sure you wish to remove this neighborhood and all of its content?')) this.onSubmitDestroy(e)
                                }}>
                                    Remove neighborhood
                                </Button>
                                <Button onClick={this.handleClickCloseDestroy}>
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

export default AdminNeighborhood;
