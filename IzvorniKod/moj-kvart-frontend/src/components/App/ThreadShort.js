import React, {Component} from 'react';
import FormDialog from "./FormDialog";
import dateFormat from 'dateformat';
import './ThreadShort.css';
import FormDialogDeleteThread from './FormDialogDeleteThread';

class ThreadShort extends Component {

    handleClick = (e) => {
        this.props.history.push('/forum/threads/' + this.props.thread.id);
    };


    render() {
        const {id, title, openedBy, openedOn, role} = this.props.thread;

        let changeTitle;
        let deleteThread;
        if(this.props.role === 'MODERATOR'){
            changeTitle = <FormDialog threadShort={this.props.thread} 
                componentDidMount={this.props.componentDidMount} /> 
            deleteThread = <FormDialogDeleteThread threadShort={this.props.thread} 
                componentDidMount={this.props.componentDidMount} /> 
        } else {
            changeTitle = ''
        }


        return (
            <div className='vanjska'>
                <a className='movers_row_a'
                    onClick={this.handleClick}> 
                    {title} 
                </a>
                <div className='movers_row_div'>
                     {openedBy.username}
                </div>
                <div className='movers_row_div'>
                    {dateFormat(openedOn, "d/m/yy")}
                </div>
                {changeTitle}
                {deleteThread}
           </div>
        );
    }
}

export default ThreadShort;
