import React, { MutableRefObject } from 'react';
import axios from 'axios';
import Disclaimer from "../components/Disclaimer";
import styles from '../styles/MiDOKPage.module.css'
import { useNavigate, useLocation } from 'react-router-dom';
import LoadingScreenComponent from '../components/LoadingScreenComponent';
import Button from '../components/Button';
import logo from "../images/midok.jpg"


const MiDOKPage = () => {
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = React.useState<boolean>(true);
    const [response, setResponse] = React.useState<string>("");
    const location = useLocation();
    const prompt = location.state.prompt;

    const apiUrl: string = "https://ai-hackathon-ap.onrender.com/chat";
    const postData: Record<string, string> = {"message" : prompt};

    const apiCallMade: MutableRefObject<boolean> = React.useRef(false);

    const getMiDOKResponse = async (apiUrl: string, postData: Record<string, string>) => {
        try {
            const response = await axios.post(apiUrl, postData, {
                headers: {
                    "Content-Type" : "application/json",
                },
            });
            setResponse(response.data.response);
        } catch (error) {
            console.error("Error: ", error);
        }
    }

    React.useEffect(() => {
        const fetchData = async () => {
            if (!apiCallMade.current) {
                try {
                    apiCallMade.current = true;
                    await getMiDOKResponse(apiUrl, postData);
                } finally {
                    setIsLoading(false);
                }
            }
        };
        fetchData();
        console.log(response);
    }, []);

    const handleClick = () => navigate("/")

    return (
        <>  
            <div className={styles.container}>
                {
                   isLoading ? <LoadingScreenComponent /> : (
                        <div className={styles.chatBox} >
                            <div className={styles.sub1}>
                                <img className={styles.icon} src={logo} />
                                <div className={styles.text} >
                                    {response}
                                </div>
                            </div>
                            <Button text="HOME" onClick={handleClick} />
                        </div>
                   )
                }
                <Disclaimer />
            </div>
        </>
    );
};

export default MiDOKPage;