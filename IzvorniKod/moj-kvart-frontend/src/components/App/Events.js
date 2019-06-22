import React, {Component} from 'react';
import AppHeader from './AppHeader';
import Card from './Card';
import AcceptedEvent from './AcceptedEvent';
import SuggestedEvent from './SuggestedEvent';
import Button from "@material-ui/core/es/Button/Button";
import Dialog from "@material-ui/core/Dialog/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent/DialogContent";
import TextField from "@material-ui/core/TextField/TextField";
import DialogActions from "@material-ui/core/DialogActions/DialogActions";
import DateTime from "react-datetime";
import './Events.css';

class Events extends Component {
    state = {
        acceptedEvents: [],
        open: false,
        openEdit: false,
        suggestTitle: '',
        suggestLocation: '',
        suggestStart: null,
        suggestEnd: null,
        suggestShortDescription: '',
        editTitle: '',
        editDescription: '',
        editLocation: '',
        editStart: null,
        editEnd: null,
        error: '',
        errorEdit: '',
        suggestedEvents: []
    }

    componentDidMount = () => {
        fetch('/events/accepted')
            .then(data => data.json())
            .then(events => this.setState({acceptedEvents: events}))

        fetch('/events/suggested')
            .then(data => data.json())
            .then(events => this.setState({suggestedEvents: events}))
            .catch(console.error)
    };


    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    handleStartDate = (date) => {
        this.setState({suggestStart: date})
    };

    handleEndDate = (date) => {
        this.setState({suggestEnd: date})
    };

    handleEditStartDate = (date) => {
        this.setState({editStart: date})
    }

    handleEditEndDate = (date) => {
        this.setState({editEnd: date})
    }

    handleClickOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false, error: ''});
    };

    handleClickOpenEdit = () => {
        this.setState({openEdit: true});
        this.componentDidMount();
    }

    handleCloseEdit = () => {
        this.setState({openEdit: false, errorEdit: ''});
    };

    onSubmit = (e) => {
        e.preventDefault();

        const data = {
            title: this.state.suggestTitle,
            description: this.state.suggestShortDescription,
            location: this.state.suggestLocation,
            start: this.state.suggestStart,
            end: this.state.suggestEnd
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/events/suggested', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400 && response.status != 401) {
                    this.setState({open: false});
                    alert("Event was succesfully suggested!");
                    this.setState({error: ''})
                    this.setState({suggestTitle: ''});
                    this.setState({suggestLocation: ''});
                    this.setState({suggestShortDescription: ''});
                    this.setState({suggestStart: null});
                    this.setState({suggestEnd: null});
                    this.componentDidMount();
                } else {
                    this.setState({error: "Suggesting event failed: " + response.message})
                }
            })
    };

    acceptEvent = (id) => {
        const options = {
            method: 'POST'
        };

        let mapping = '/events/suggested/' + id;

        return fetch(mapping, options)
            .then(response => {
                if (response.ok) {
                    alert("Event accepted");
                    this.componentDidMount();
                } else {
                    alert("Accepting event failed: " + response.json().message);
                }
            })
    }

    editEvent = (id) => {
        const data = {
            title: this.state.editTitle,
            description: this.state.editDescription,
            location: this.state.editLocation,
            start: this.state.editStart,
            end: this.state.editEnd
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        let mapping = '/events/suggested/' + id + '/modify';

        return fetch(mapping, options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400 && response.status != 401) {
                    alert("Event accepted after editing");
                    this.setState({errorEdit: ''})
                    this.setState({editTitle: ''});
                    this.setState({editLocation: ''});
                    this.setState({editDescription: ''});
                    this.setState({editStart: null});
                    this.setState({editEnd: null});
                    this.componentDidMount();
                } else {
                    this.setState({errorEdit: "Editing event failed: " + response.message})
                }
            })
    }

    declineEvent = (id) => {
        const options = {
            method: 'DELETE'
        };

        let mapping = '/events/suggested/' + id;

        return fetch(mapping, options)
            .then(response => {
                if (response.ok) {
                    alert("Event declined");
                    this.componentDidMount();
                } else {
                    alert("Declining event failed: " + response.json().message);
                }
            })
    }

    render() {
        let listOfSuggestedEvents;
        if (this.props.role === 'MODERATOR') {
            listOfSuggestedEvents = <Card title='Suggested events'> {
                this.state.suggestedEvents.map(event =>
                    <div>
                        <SuggestedEvent key={event.id} event={event} {...this.props}/>
                        <button className='accept_button' onClick={() => this.acceptEvent(event.id)}>Accept</button>
                        <button className='edit_event_button' onClick={this.handleClickOpenEdit}>Edit</button>
                        <button className='decline_button' onClick={() => this.declineEvent(event.id)}>Decline
                        </button>
                        <Dialog
                            open={this.state.openEdit}
                            onClose={this.handleCloseEdit}
                        >
                            <DialogTitle>Change event information</DialogTitle>
                            <DialogContent>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label="Title"
                                    fullWidth
                                    name='editTitle'
                                    onChange={this.handleChange}
                                    value={this.state.editTitle}
                                />
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label="Location"
                                    fullWidth
                                    name='editLocation'
                                    onChange={this.handleChange}
                                    value={this.state.editLocation}
                                />
                                <label>Event start</label>
                                <DateTime onChange={this.handleEditStartDate} value={this.state.editStart}/>
                                <label>Event end</label>
                                <DateTime onChange={this.handleEditEndDate} value={this.state.editEnd}/>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    label="Short description"
                                    fullWidth
                                    name='editDescription'
                                    onChange={this.handleChange}
                                    value={this.state.editDescription}
                                />
                            </DialogContent>
                            <div className='error'>{this.state.errorEdit}</div>
                            <DialogActions>
                                <Button onClick={() => this.editEvent(event.id)}>
                                    Accept event
                                </Button>
                                <Button onClick={this.handleCloseEdit}>
                                    Cancel
                                </Button>
                            </DialogActions>
                        </Dialog>
                    </div>
                )
            }
            </Card>
        } else {
            listOfSuggestedEvents = ''
        }

        return (
            <div>
                <AppHeader {...this.props} onLogout={this.props.onLogout}/>
                <Card title='Events'> {
                    this.state.acceptedEvents.map(event =>
                        <div>
                            <AcceptedEvent key={event.id}
                                           event={event} {...this.props}/>
                            <div style={{height: 10}}/>
                        </div>
                    )
                }
                    <button className='event_button' onClick={this.handleClickOpen}>Suggest new event</button>
                    <Dialog
                        open={this.state.open}
                        onClose={this.handleClose}
                    >
                        <DialogTitle>Enter new information</DialogTitle>
                        <DialogContent>
                            <TextField
                                autoFocus
                                margin="dense"
                                label="Title"
                                fullWidth
                                name='suggestTitle'
                                onChange={this.handleChange}
                                value={this.state.suggestTitle}
                            />
                            <TextField
                                autoFocus
                                margin="dense"
                                label="Location"
                                fullWidth
                                name='suggestLocation'
                                onChange={this.handleChange}
                                value={this.state.suggestLocation}
                            />
                            <label>Event start</label>
                            <DateTime onChange={this.handleStartDate} value={this.state.suggestStart}/>
                            <label>Event end</label>
                            <DateTime onChange={this.handleEndDate} value={this.state.suggestEnd}/>
                            <TextField
                                autoFocus
                                margin="dense"
                                label="Short description"
                                fullWidth
                                name='suggestShortDescription'
                                onChange={this.handleChange}
                                value={this.state.suggestShortDescription}
                            />
                        </DialogContent>
                        <div className='error'>{this.state.error}</div>
                        <DialogActions>
                            <Button onClick={this.onSubmit}>
                                Suggest Event
                            </Button>
                            <Button onClick={this.handleClose}>
                                Cancel
                            </Button>
                        </DialogActions>
                    </Dialog>
                </Card>
                {listOfSuggestedEvents}
            </div>
        );
    }
}

export default Events;

