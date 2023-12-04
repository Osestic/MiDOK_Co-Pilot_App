import Disclaimer from "../components/Disclaimer";
import SearchBarComponent from "../components/SearchBarComponent";
import healthInfo from "../images/healthcareinfo.png"
import styles from '../styles/MiDOKSearchPage.module.css'
import { useNavigate, useParams } from 'react-router-dom';


const MiDOKSearchPage = () => {
    const navigate = useNavigate();

    let diseaseClassificationId: string | undefined = useParams().id;
    let diseaseClassification: string = "None";
    let data: string[] = ["Heart Disease", "Alzheimer's Disease", "Arthritis", "Cancer", "Diabetes", "High Blood Pressure"];

    const handleClick = () => {
        navigate("/diabetes");
    }

    if (diseaseClassificationId === "common-diseases") {
        diseaseClassification = "Common Diseases";
    } else if (diseaseClassificationId === "hereditary-diseases") {
        diseaseClassification = "Hereditary Diseases";
    } else if (diseaseClassificationId === "mental-health-issues") {
        diseaseClassification = "Mental Health Issues";
    }
    
    return (
        <>  
            <div className={styles.container}>
                <img className={styles.image} src={healthInfo} />
                <p className={styles.text}>{diseaseClassification}</p>
                <SearchBarComponent placeholder="Search MiDOK..." data={data} onClick={handleClick} />
                <Disclaimer />
            </div>
        </>
    );
};

export default MiDOKSearchPage;