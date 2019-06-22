import AppHeader from "./AppHeader";
import React, {Component} from 'react';

class AfterLoginPage extends Component {

    onLogout = () => {
        this.setState({loggedIn: false});
    };

    render() {
        return (
            <div>
                <AppHeader onLogout={this.onLogout}/>
            </div>
        );
    }
}

export default AfterLoginPage;