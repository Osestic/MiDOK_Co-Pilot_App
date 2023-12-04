import styles from '../styles/components/LoadingScreenComponent.module.css';
import logo from "../images/midok.jpg";
import { Bars } from 'react-loading-icons';

const LoadingScreenComponent = () => {

    return (
        <>  
            <div className={styles.container}>  
                <img src={logo} />
                <Bars className={styles.loadingAnimation} speed={0.75} />
            </div>
        </>
    );
};

export default LoadingScreenComponent;