import React, {Component} from 'react';
import Form from "./Form";
import Card from "./Card";
import "./SignupForm.css";
import DropdownMenu from "./DropdownMenu";
import { Link } from 'react-router-dom';

class SignupForm extends Component {
    state = {
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        street: '',
        error: '',
        streets: []
    };

    componentDidMount() {
        fetch('/streets')
            .then(response => {
                return response.json();
            }).then(streets => {
            this.setState({streets: streets})
        })
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    onSubmit = (e) => {
        e.preventDefault();

        let item = document.getElementById("signupStreet");
        let selectedText = item.options[item.selectedIndex].text
        this.state.street = selectedText;
        //this.setState({street: selectedText});

        const data = {
            username: this.state.username,
            password: this.state.password,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            street: this.state.street
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/residents', options)
            .then(response => response.json())
            .then(response => {
                if (response.status != 400) {
                    this.setState({error: ''});
                    alert("Sign up was successful")
                    this.props.history.push('/');
                }
                else {
                    this.setState({error: 'Registration failed: ' + response.message});
                }
            })
    };

    render() {
        let streets = this.state.streets;
        let optionItems = streets.map(street => <option key={street.id}>{street.name}</option>);

        return (
            <div className='SignupForm'>
                <Card title='Create new MyNeighborhood account'>
                    <div className='login_sentence'>
                        <span className='or'>or </span>
                        <Link className='link' to='/user/login'>
                            sign in to your account
                        </Link>
                    </div>
                    <Form onSubmit={this.onSubmit}>
                        <Form.Row label='Username'>
                            <input name='username'
                                onChange={this.handleChange}
                                value={this.state.username}
                                placeholder='EddieVedder'/>
                        </Form.Row>
                        <Form.Row label='Password'>
                            <input name='password'
                                onChange={this.handleChange}
                                value={this.state.password}
                                type='password'
                                placeholder='********'/>
                        </Form.Row>
                        <Form.Row label='First name'>
                            <input name='firstName'
                                onChange={this.handleChange}
                                value={this.state.firstName}
                                placeholder='Eddie'/>
                        </Form.Row>
                        <Form.Row label='Last name'>
                            <input name='lastName'
                                onChange={this.handleChange}
                                value={this.state.lastName}
                                placeholder='Vedder'/>
                        </Form.Row>
                        <Form.Row label='Email'>
                            <input name='email'
                                onChange={this.handleChange}
                                value={this.state.email}
                                placeholder='ev@gmail.com'/>
                        </Form.Row>
                        <Form.Row label='Street'>
                            <DropdownMenu id='signupStreet'
                               className='dropdown_menu'  >
                                {optionItems}
                            </DropdownMenu>
                        </Form.Row>
                        <div className='error'>{this.state.error}</div>
                        <button id='sign_up_button' type='submit'>Sign up</button>
                    </Form>
                </Card>
            </div>
        )
    }
}

export default SignupForm;

