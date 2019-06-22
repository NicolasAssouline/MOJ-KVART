import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import './FormDialogChangePost.css';

export default class FormDialogChangePost extends React.Component {
    state = {
        open: false,
        newContent: '',
        error: ''
    };

    handleClickOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false, error: ''});
    };

    onContentChangeSubmit = () => {
        //console.log(this.props.componentDidMount);
        const body = `${this.state.newContent}`;
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: body
        };

        fetch('/forum/posts/' + this.props.post.id, options)
            .then(response => {
                if (response.status === 400) {
                    this.setState({error: 'Empty content'})
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
        console.log(this.state.newContent);
    }


    render() {
        const {content, id} = this.props.post;
        return (
            <span>
        <button className='edit_post_button'
                onClick={this.handleClickOpen}>
            Edit
        </button>
        <Dialog
            open={this.state.open}
            onClose={this.handleClose}
            aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">
            Change content
          </DialogTitle>
          <DialogContent>
            <DialogContentText>
                {content}
            </DialogContentText>
            <textarea
                rows='3'
                cols='70'
                autoFocus
                margin="dense"
                id="name"
                label="New content"
                fullWidth
                name='newContent'
                value={this.state.newContent}
                onChange={this.handleChange}
            />
          </DialogContent>
          <div> {this.state.error} </div>
          <DialogActions>
            <Button onClick={this.onContentChangeSubmit} color="primary">
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
