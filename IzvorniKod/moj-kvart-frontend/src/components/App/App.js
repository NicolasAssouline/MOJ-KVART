import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Login from "./Login";
import Home from "./Home";
import SignupForm from "./SignupForm";
import './App.css';
import Forum from './Forum.js';
import AdminAccounts from './AdminAccounts'
import AdminNeighborhood from './AdminNeighborhood'
import AdminProfile from "./AdminProfile";
import Thread from "./Thread";
import Council from "./Council";
import Events from "./Events";
import Profile from "./Profile";
import './Forum.css';

class App extends Component {

    state = {
        loading: true,
        loggedIn: false,
        role: '',
        id: ''
    };

    componentDidMount() {
        fetch('/user', { credentials: 'include' })
            .then(response => response.json())
            .then(user => {
                    this.setState({loggedIn: true, role: user.role, id: user.id})
                }
            ).catch(console.error)
    }

    onLogin = (r, id) => {
        this.setState( { loggedIn: true, role: r, id: id});
    };

    onLogout = () => {
        this.setState({ loggedIn: false, role: '', id: '' });
    };

    render() {
        return (
            <BrowserRouter>
                <div className="app">
                    <Switch>
                        <Route path='/' exact component={Home}/>
                        <Route path='/user/login' exact render={
                            (props) => <Login {...props}
                                onLogin={this.onLogin}/>} />
                        <Route path='/user/signup' exact
                            component={SignupForm}/>
                        <Route path='/forum/threads' exact render={
                            (props) => <Forum className='Forum.css' {...props}
                                onLogout={this.onLogout}
                                role={this.state.role}/>} />
                        <Route path='/admin/profile' exact render={
                            (props) => <AdminProfile {...props}
                                onLogout={this.onLogout}/>} />
                        <Route path={'/forum/threads/:id'} render={
                            (props) => <Thread {...props}
                                onLogout={this.onLogout}
                                role={this.state.role}
                                userId={this.state.id}/>} />
                        <Route path='/admin/accounts' exact render={
                            (props) => <AdminAccounts {...props}
                                onLogout={this.onLogout}/>} />
                        <Route path='/admin/neighborhoods' exact render={
                             (props) => <AdminNeighborhood {...props}
                                onLogout={this.onLogout}/>} />
                        <Route path='/council' exact render={
                            (props) => <Council {...props}
                                onLogout={this.onLogout}
                                role={this.state.role}/>} />
                        <Route path='/events' exact render={
                            (props) => <Events {...props}
                                onLogout={this.onLogout}
                                role = {this.state.role}/>} />
                        <Route path='/profile' exact render={
                            (props) => <Profile {...props}
                                onLogout={this.onLogout}/>} />
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
