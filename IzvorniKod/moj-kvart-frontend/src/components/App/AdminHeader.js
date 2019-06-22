import React, {Component} from 'react';
import {Link} from "react-router-dom";
import './AdminHeader.css'
import logo from './mojkvart-bijelo.png';

class AdminHeader extends Component {
    logout = () => {
        fetch('/logout').then(resp => {
            this.props.onLogout();
            this.props.history.push('/');
        });
    };

    render() {
        return (
            <header className='AdminHeader'>
                <img className='logo' src={logo} alt="img"/>
                <div className='logo_text_admin'>
                    MyNeighborhood
                </div>
                <Link to='/admin/accounts'>Open account</Link>
                <Link to='/admin/neighborhoods'>Create/Remove neighborhood</Link>
                <div className='header_right'>
                    <Link to='/admin/profile'>Profile</Link>
                    <button className='logout_button_admin'
                            onClick={this.logout}>
                        Logout
                    </button>
                </div>
            </header>
        )
    }
}

export default AdminHeader;
