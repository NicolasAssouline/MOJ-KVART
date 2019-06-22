import React, {Component} from 'react';
import './DropdownMenu.css'

class DropdownMenu extends Component {
    render() {
        const {children, ...otherProps} = this.props;

        return (
            <select className='DropMenu' {...otherProps}>
                {children}
            </select>
        )
    }
}

export default DropdownMenu;