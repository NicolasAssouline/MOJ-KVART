import React, {Component} from 'react';
import Form from "./Form";
import Card from "./Card";
import './Login.css';
import {Link} from 'react-router-dom';

class Login extends Component {

    state = {
        username: '',
        password: '',
        error: '',
    };

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    onSubmit = (e) => {
        e.preventDefault();
        const body = `username=${this.state.username}&password=${this.state.password}`;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: body
        };
        fetch('/login', options)
            .then(response => {
                if (response.status === 401) {
                    this.setState({error: 'Login failed.'})
                    //this.setState({username: ''})
                    //this.setState({password: ''})
                } else {
                    // TODO: Maybe check response from /user in future.
                    fetch('/user', {credentials: 'include'})
                        .then(resp => resp.json())
                        .then(user => {
                            this.props.onLogin(user.role, user.id);
                            if (user.role === 'ADMIN') {
                                this.props.history.push('/admin/profile');
                            } else {
                                this.props.history.push('/forum/threads');
                            }
                        });
                }
            })
    };

    render() {
        return (
            <div className="Login">
                <Card title='Log in to MyNeighborhood'>
                    <div className='create_acc'>
                        <span className='or'>or </span>
                        <Link className='link' to='/user/signup'>
                            create an account
                        </Link>
                    </div>
                    <Form className='input_from' onSubmit={this.onSubmit}>
                        <Form.Row label='Username'>
                            <input name='username' onChange={this.handleChange}
                                   value={this.state.username}
                                   placeholder='User'/>
                        </Form.Row>
                        <Form.Row label='Password'>
                            <input name='password' type='password'
                                   onChange={this.handleChange}
                                   value={this.state.password}
                                   placeholder='********'/>
                        </Form.Row>
                        <div className='error'>{this.state.error}</div>
                        <button id='log_in_button' type='submit'>Login</button>
                    </Form>
                </Card>
            </div>
        );
    }
}

export default Login;
