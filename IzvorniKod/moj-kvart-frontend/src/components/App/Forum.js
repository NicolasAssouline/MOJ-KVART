import React, { Component } from 'react';
import AppHeader from './AppHeader';
import Card from './Card';
import './Forum.css';
import ThreadShort from './ThreadShort';
import Form from './Form';

class Forum extends Component {
    state = {
        threads: [],
        title: '',
        error: ''
    };

    componentDidMount = () => {
    fetch('/forum/threads')
        .then(data => data.json())
        .then(threads => this.setState({threads: threads}))   
   };


   handleChange = (event) => {
       this.setState({
           [event.target.name]: event.target.value
       });
       this.setState({error: ''});
   };

   onSubmit = (e) => {
       e.preventDefault();
        
       const data = {
           title: this.state.title
        };
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: data.title
        };
        
        return fetch('/forum/threads/', options)
            .then(response => {
                 if(response.ok) {
                     this.componentDidMount();
                     this.setState({title: ''})
                     this.setState({error: ''})
                 } else {
                   this.setState({error: 'Empty title.'}); 
                 }
            })
   };


    render(){
        return(
            <div>
                <AppHeader {...this.props} onLogout={this.props.onLogout}/> 
                <Card title='Threads'>
                    <div className='line' />
                    <div className='about_forum'>
                        Forum Moj kvart je dizajniran kao mjesto za okupljanje stanovnika iz kvarta.<br/>
                        Molimo da teme koje otvarate imaju sadržaj relevantan za kvart ili kvartovski život.<br/>
                        Teme neprikladnog sadržaja bit će uklonjene.<br/>
                    </div>
                     <div className='line' /> 
                     <div>
                         <div className='title_percentage'>
                             Title
                         </div>
                         <div className='author_percentage'>
                             Author
                         </div>
                         <div className='date_percentage'>
                             Date
                         </div>
                         <div className='empty_line_to_fix_design'/>
                     </div>

                    {
                    this.state.threads.map(thread =>
                        <ThreadShort key={thread.id}  
                            thread={thread} 
                            role={this.props.role}
                            {...this.props}
                            onChange={this.handleChange}
                            componentDidMount={this.componentDidMount}
                            />)
                    }

                    <div className='new_thread_card'>
                        <Form onSubmit={this.onSubmit}>
                            <div className='create_new_thread'> 
                                Create new thread 
                            </div>
                            <Form.Row label=''>
                                <input className='create_thread_input' 
                                    name='title' 
                                    onChange={this.handleChange} 
                                    value={this.state.title}
                                    placeholder='Title...'/>
                            </Form.Row>
                            <div className='error'>{this.state.error}</div>
                            <button className='new_thread_button' 
                                type="submit">Submit
                            </button>
                        </Form>
                    </div>
            </Card>
            </div>
        );
    }

}

export default Forum;
