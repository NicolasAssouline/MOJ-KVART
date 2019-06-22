import React, { Component } from 'react';
import dateFormat from 'dateformat';
import './Report.css';

class Report extends Component {
    render() {
        const {id, content, heldOn, publishedOn, publishedBy} = this.props.report;

        return(
            <div>
            <div className='report_card'>
                <div className='posted_on_card'> 
                    Held on {dateFormat(heldOn, 'dd/mm/yy')}
                </div>    
                <div>
                    {content}
                </div>
                <div className='posted_by_card'> 
                    {publishedBy.username}
                </div>
            </div>
        </div>
       );
    }

}

export default Report;

