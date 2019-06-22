import React from 'react';
import Button from "@material-ui/core/es/Button/Button";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import './FormDialogDeleteThread.css';

export default class FormDialogDeletePost extends React.Component {
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

    onClickDelete = () => {
        const options = {
            method: 'DELETE'
        };

        let mapping = '/forum/posts/' + this.props.post.id;

        return fetch(mapping, options)
            .then(response => {
                if (response.ok) {
                    this.props.componentDidMount();
                } else {
                }
            })
    };

    render() {
        return (
            <span>
                <button className='delete_post_button' variant="outlined" color="primary" onClick={this.handleClickOpen}>
                    Delete
                </button>
                <Dialog
                    open={this.state.open}
                    onClose={this.handleClose}
                    aria-labelledby="form-dialog-title"
                >
                    <DialogTitle id="form-dialog-title">Are you sure you want to delete this post?</DialogTitle>
                    <DialogContent>
                    </DialogContent>
                    <div> {this.state.error} </div>
                    <DialogActions>
                        <Button onClick={this.onClickDelete} color="primary">
                            Delete
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
