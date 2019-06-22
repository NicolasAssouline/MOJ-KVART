import React, {Component} from 'react';
import Post from './Post';
import Card from './Card';
import AppHeader from './AppHeader';
import Form from './Form';
import Button from './Button';
import './Thread.css';

class Thread extends Component {
    state = {
        posts: [],
        content: '',
        error: ''
    }

    componentDidMount = () => {
       const threadId = this.props.match.params.id; // Fetched from URL.
       fetch('/forum/threads/'+ threadId)
        .then(data => data.json())
        .then(posts => this.setState({posts: posts}))   
   };

   handleChange = (event) => {
       this.setState({
           [event.target.name]: event.target.value
       });
   };

   onSubmit = (e) => {
        e.preventDefault();

        const data = {
            content: this.state.content
        };
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: data.content
        };
        
        const threadId = this.props.match.params.id; // Fetched from URL.

        return fetch('/forum/threads/' + threadId, options)
            .then(response => {
                 if(response.ok) {
                     this.componentDidMount();
                     this.setState({content: ''})
                     this.setState({error: ''})
                 } else {
                   this.setState({error: 'Empty post'}); 
                 }
            })
   };

    render() {
        return (
            <div> 
               <AppHeader {...this.props} onLogout={this.props.onLogout}/>
               <Card title='Posts'>{
                    this.state.posts.map(post =>
                        <div>
                            <Post key={post.id} post={post}
                                role={this.props.role}
                                userId={this.props.userId}
                                componentDidMount={this.componentDidMount}/>
                            <div style={{height: 10}}/>
                        </div>)
               }
                <div className='error'>{this.state.error}</div>
                <Form onSubmit={this.onSubmit}>
                    <Form.Row label=''>
                        <textarea className='reply_textarea' name='content' 
                            onChange={this.handleChange} value={this.state.content} 
                            placeholder='Reply...' rows='3'/>
                </Form.Row>
                <button className='new_post_button' type="submit">Submit post</button>
                </Form>    
                </Card>

            </div>
        );
    }
}

export default Thread;
