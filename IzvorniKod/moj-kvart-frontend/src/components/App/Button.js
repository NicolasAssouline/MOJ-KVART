import React, {Component} from 'react';
import './Button.css';

class Button extends Component {
    render() {
        const {children, ...otherProps} = this.props;

        return (
            <button className='Button' {...otherProps}>{children}</button>
        )
    }
}

export default Button;
