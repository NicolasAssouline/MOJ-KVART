import React, { Component } from 'react';
import AppHeader from './AppHeader';
import Card from './Card';
import Form from './Form';
import Report from './Report';
import DateTime from 'react-datetime';
import './react-datetime.css';
import './Council.css'

class Council extends Component {
    state = {
        reports: [],
        content: '',
        heldOn: new Date(), 
        error: ''
    }

    componentDidMount() {
        fetch('/council')
            .then(data => data.json())
            .then(reports => this.setState({reports: reports}))
    }

    handleChange = (event) => {
        this.setState({error: ''})
        this.setState({
            [event.target.name]: event.target.value
        });
    };

    handleDate = (date) => {
        this.setState({heldOn: date})
    };

    onSubmit = (e) => {
        e.preventDefault();
        
        const data = {
            content: this.state.content,
            heldOn: this.state.heldOn
        };
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/council', options)
            .then(response => {
                if(response.ok) {
                    this.componentDidMount();
                    this.setState({content: '', error: ''})
            } else {
                this.setState({error: 'Empty report.'})
            }})
    };

    render(){

        let submitReport;
        if(this.props.role === 'COUNCILOR') {
            submitReport =  <div className='new_report_card'>
                <Form onSubmit={this.onSubmit}>
                    <div className='create_new_report'> 
                        Create new report 
                    </div>
                    <Form.Row label=''>
                        <input className='create_report_input'
                            name='content' 
                            onChange={this.handleChange}
                            value={this.state.content}
                            placeholder='Report content...'
                        />
                    </Form.Row>
                    <div className='error'>{this.state.error}</div>
                    <label> Held on </label>
                    <DateTime onChange={this.handleDate}/>    
                    <button type="submit" className='new_report_button'>
                        Submit report
                    </button>
            </Form></div>
        } else {
            submitReport = '' 
        }


        return(
            <div>
                <AppHeader title='Council reports' 
                    {...this.props} onLogout={this.props.onLogout}/>
                <Card title='Council reports'>
                    <div>
                    <div className='line' />
                        <div>
                            Kvartovsko vijeće je tu da radi na unaprjeđenju Vašeg kvarta.<br/>
                            Na ovoj stranici možete naći izvješća sa svih održanih sastanaka.<br/>
                            Za prijedloge i poboljšanja obratite se svom kvartovskom vijećniku.<br/>
                        </div>
                    </div>
                     <div className='line' />
                    {
                        this.state.reports.map(report =>
                            <div>
                                <Report key={report.id}
                                        report={report} {...this.props}/>
                                <div style={{height: 10}}/>
                            </div>)
                    }
                {submitReport}
                </Card>
           </div>
        );
    }
}

export default Council;
