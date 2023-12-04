import Disclaimer from '../components/Disclaimer';
import '../styles/HomePage.css'
import logo from "../images/midok.jpg"
import HomeCardComponent from '../components/HomeCardComponent';
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
    const navigate = useNavigate();

    const handleCardClick = (userType : string) => {
        navigate(`user/${userType}`);
    }

    return (
        <>
            <div className="container">
                <p className="midok">MiDOK</p>
                <img src={logo} />
                <p className="who">Who are you?</p>
                <div className="card">
                    <HomeCardComponent onClick={() => handleCardClick("student")} text="Student" />
                    <HomeCardComponent onClick={() => handleCardClick("health-professional")} text="Health Professional" />
                    <HomeCardComponent onClick={() => handleCardClick("non-health-professional")} text="Non-Health Professional" />
                </div>
                <Disclaimer />
            </div>
        </>
    )
};

export default HomePage;