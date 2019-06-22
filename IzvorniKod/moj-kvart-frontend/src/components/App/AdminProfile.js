import AdminHeader from './AdminHeader';
import React, {Component} from 'react';
import Card from "./Card";
import './AdminProfile.css'
import Table from "@material-ui/core/Table/Table";
import TableRow from "@material-ui/core/TableRow/TableRow";
import TableBody from "@material-ui/core/TableBody/TableBody";
import TableCell from "@material-ui/core/TableCell/TableCell";

class AdminProfile extends Component {

    state = {
        user: {
            id: '',
            name: '',
            role: ''
        }
    }

    componentDidMount() {
        fetch('/user')
            .then(data => data.json())
            .then(user => this.setState({user: user}))
    }

    render() {
        return (
            <div>
                <AdminHeader {...this.props} onLogout={this.props.onLogout}/>
                <div className='Info'>
                    <Card title='Profile information'>
                        <Table>
                            <TableBody>
                                <TableRow>
                                    <TableCell>
                                        <label>Username</label>
                                    </TableCell>
                                    <TableCell>
                                        <input type="text" value={this.state.user.name}
                                               readOnly='readOnly'/>
                                    </TableCell>
                                </TableRow>

                                <TableRow>
                                    <TableCell>
                                        <label>Role</label>
                                    </TableCell>
                                    <TableCell>
                                        <input type="text" value={this.state.user.role}
                                               readOnly='readOnly'/>
                                    </TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </Card>
                </div>
            </div>
        )
    }
}

export default AdminProfile;
