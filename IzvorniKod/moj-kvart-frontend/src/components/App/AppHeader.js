import React, {Component} from 'react';
import './AppHeader.css';
import {Link} from "react-router-dom";
import logo from './mojkvart-bijelo.png';


class AppHeader extends Component {
    logout = () => {
        fetch('/logout').then(resp => {
            this.props.onLogout();
            this.props.history.push('/');
        });
    };

    render() {
        return (
            <header className='AppHeader'>
                <img className='logo' src={logo} alt="img"/>
                <span className='logo_text'>
                    MyNeighborhood
                </span>
                <div className='fce'>
                    <Link className='forum_button' to='/forum/threads'>
                        Forum
                    </Link>
                    <Link className='Council' to='/council'>
                        Council
                    </Link>
                    <Link className='Events' to='/events'>
                        Events
                    </Link>
                </div>
                <div className='app_header_right'>
                    <Link className='Resident profile' to='/profile'>
                        Profile
                    </Link>
                    <button className='logout_button' onClick={this.logout}>
                        Logout
                    </button>
                </div>
            </header>
        )
    }
}

export default AppHeader;
