import React from 'react';
import Button from "@material-ui/core/es/Button/Button";
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import './FormDialog.css'

export default class FormDialog extends React.Component {
    state = {
        open: false,
        newTitle: '',
        error: ''
    };

    handleClickOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false, error: ''});
    };

    onTitleChangeSubmit = () => {
        const body = `${this.state.newTitle}`;
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: body
        };
        fetch('/forum/threads/' + this.props.threadShort.id, options)
            .then(response => {
                if (response.status === 400) {
                    this.setState({error: 'Empty title'})
                }
                else {
                    this.setState({open: false});
                    this.props.componentDidMount();
                }
            })


    };

    handleChange = (event) => {
        this.setState({
            error: '',
            [event.target.name]: event.target.value
        });
    }


    render() {
        const {title, id} = this.props.threadShort;
        return (
            <span>
                <button className='edit_thread_button' 
                    onClick={this.handleClickOpen}>
                    Edit
                </button>
                <Dialog
                    open={this.state.open}
                    onClose={this.handleClose}
                    aria-labelledby="form-dialog-title"
                >
                    <DialogTitle id="form-dialog-title">
                        Change title
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            {title}
                        </DialogContentText>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="name"
                            label="New title"
                            fullWidth
                            name='newTitle'
                            value={this.state.newTitle}
                            onChange={this.handleChange}
                        />
                    </DialogContent>
                    <div> {this.state.error} </div>
                    <DialogActions>
                        <Button onClick={this.onTitleChangeSubmit} color="primary">
                            Change
                        </Button>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                    </DialogActions>
                </Dialog>
            </span>
        );
    }
}
