import React, {Component} from 'react';
import './SuggestedEvent.css';
import dateFormat from 'dateformat';
import humanizeDuration from 'humanize-duration';

class SuggestedEvent extends Component {
    render() {
        const {id, title, description, location, start, end} = this.props.event;

        let duration = null;
        let parsedDate = new Date(start);
        let adjustedDate = new Date(parsedDate.getTime() - parsedDate.getTimezoneOffset() * 60000);

        if(end === null) {
            duration = "unknown";
        } else {
            duration = humanizeDuration(new Date(end) - new Date(start));
        }

        return (
            <div className='suggested_event_card'>
                <div className='title_card'>
                    {title}
                </div>
                <div>
                    Short description: {description}
                </div>
                <div className='colored'>
                    Location: {location}
                </div>
                <div>
                    Start date: {dateFormat(start, 'dd/mm/yy')}
                </div>
                <div className='colored'>
                    Start time: {dateFormat(adjustedDate, 'h:MM TT')}
                </div>
                <div>
                    Duration: {duration}
                </div>
            </div>
        );
    }
}

export default SuggestedEvent;
