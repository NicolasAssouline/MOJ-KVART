import React, {Component} from 'react';
import {Link} from "react-router-dom";
import './Header.css';
import logo from './mojkvart-bijelo.png';

class Header extends Component {
    render() {
        return (
            <div className='header'>
                <div className='container_logo'>
                    <img className='logo' src={logo} alt="img"/>
                    <span className='logo_text_header'>
                        MyNeighborhood
                    </span>
            </div>
                <div className="header-right">
                    <Link className="login" to='/user/login'>LOGIN</Link>
                    <Link className="signup" to='/user/signup'>SIGN UP</Link>
                </div>
            </div>
        )
    }
}

export default Header;
