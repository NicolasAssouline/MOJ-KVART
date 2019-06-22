import React, {Component} from 'react';
import img from '../../neighbourhood.jpg';
import Header from "./Header";
import './Home.css';
import Card from "./Card";

class Home extends Component {
    render() {
        return (
            <div>
                <Header onLogin={this.props.onLogin}/>
                <div className="Home">
                    <p>
                        <img className="img" src={img} alt="img"/>
                    </p>
                    <Card>
                        <p className="description">
                            Oduvijek si tražio mjesto na kojem će se tvoj glas čuti?<br/>
                            Ovo je forum za tebe!<br/>
                            Moj kvart je društvena mreža koja povezuje susjede i ujedno je najbolje mjesto<br/>
                            za doznati novosti i za organizaciju društvenih događaja u tvojem okruženju.<br/>
                            Napravi svoj profil potpuno besplatno i pridruži se našoj zajednici!<br/>
                        </p>
                    </Card>
                </div>
            </div>
        );
    }
}

export default Home;
