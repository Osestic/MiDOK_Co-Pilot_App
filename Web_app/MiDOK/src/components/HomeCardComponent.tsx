import React from 'react';
import styles from '../styles/components/HomeCardComponent.module.css'
import Student from "../images/student.jpg"
import HealthProfessional from "../images/doctor.jpg"
import NonHealthProfessional from "../images/nonpro.png"

const HomeCardComponent = (props : {text : string, onClick : React.MouseEventHandler<HTMLDivElement>}) => {

    const imageMap : Record<string, string> ={
        "Student": Student,
        "Health Professional": HealthProfessional,
        "Non-Health Professional": NonHealthProfessional
    }

    const imgSrc: string = imageMap[props.text];

    return (
        <>
            <div className={styles.container} onClick={props.onClick}>
                <img className={styles.icon} src={imgSrc} />
                <p className={styles.text}>{props.text}</p>
            </div>
        </>
    );
};

export default HomeCardComponent;