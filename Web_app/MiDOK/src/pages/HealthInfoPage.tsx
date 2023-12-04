import CardComponent from "../components/CardComponent";
import Disclaimer from "../components/Disclaimer";
import healthInfo from "../images/healthcareinfo.png"
import styles from '../styles/HealthInfoPage.module.css'
import { useNavigate } from 'react-router-dom';


const HealthInfoPage = () => {
    const navigate = useNavigate();

    const handleCardClick = (diseaseClassification : string) => {
        navigate(`/MiDOK-Search/${diseaseClassification}`);
    }
    
    return (
        <>  
            <div className={styles.container}>
                <img className={styles.image} src={healthInfo} />
                <p className={styles.text}>HealthCare Information</p>
                <CardComponent onClick={() => handleCardClick("common-diseases")} text="COMMON DISEASES" />
                <CardComponent onClick={() => handleCardClick("hereditary-diseases")} text="HEREDITARY DISEASES" />
                <CardComponent onClick={() => handleCardClick("mental-health-issues")} text="MENTAL HEALTH ISSUES" />
                <Disclaimer />
            </div>
        </>
    );
};

export default HealthInfoPage;