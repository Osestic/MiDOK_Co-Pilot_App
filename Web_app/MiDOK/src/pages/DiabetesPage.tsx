import React from 'react';
import CheckBoxGroupComponent from "../components/CheckBoxGroupComponent";
import Disclaimer from "../components/Disclaimer";
import Diabetes from "../images/diabetes.png"
import styles from '../styles/DiabetesPage.module.css'
import { useNavigate } from 'react-router-dom';
import DropdownMenuComponent from '../components/DropdownMenuComponent';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';
import Button from '../components/Button';


const DiabetesPage = () => {
    const navigate = useNavigate();

    const options: string[] = ["Less than a day", "2-3 days", "Over a week"];
    const [selected, setSelected] = React.useState<string>("Less than a day");

    const [sliderValue, setSliderValue] = React.useState<number>(20);

    const handleSliderChange = (value: number | number[]) => {
        setSliderValue(value as number);
    };

    const [symptoms, setSymptoms] = React.useState<Record<string, boolean>>({
        "Urinate a lot": false,
        "Are very thirsty": false,
        "Lose weight without trying": false,
        "Are very hungry": false,
        "Have blurry vision": false,
        "Feel very tired": false,
        "Have very dry skin": false,
        "Have numb hands or feet": false,
    });

    const handleCheckBoxChange = (symptom : string) => {
        setSymptoms(
            {   
                ...symptoms,
                [symptom] : !symptoms[symptom]
            }
        )
    }

    const handleDropdownArrowClick = (newSelected: string) => setSelected(newSelected);

    const processSymptoms = () => {
        let output: string = "";
        for (const[key, value] of Object.entries(symptoms)) {
            if (value == true) {
                output += key + ", "
            }
        }
        return output.slice(0, -2);
    }

    const generatePrompt = () => {
        const symptomStr: string = processSymptoms();
        if (symptomStr == "") return ""
        return `Someone is experiencing diabetes, the person is experiencing the following symptoms: ${symptomStr}. With a severity of ${sliderValue} for the past ${selected}. Do the symptoms match with diabetes symptoms. If yes, list in simple terms some possible treatment. Return with one line space between paragraphs.`;
    }

    const handleButtonClick = () => {
        const prompt: string = generatePrompt();
        if (prompt == "") return
        navigate("/MiDOK", {state: {prompt: prompt}});
    }
    
    return (
        <>  
            <div className={styles.container}>
                <img className={styles.image} src={Diabetes} />
                <div className={styles.sub1}>
                    <div className={styles.sub2}>
                        <p className={styles.text}>List of Common Symptoms for Diabetes</p>
                        <CheckBoxGroupComponent data={symptoms} onChange={handleCheckBoxChange} />
                        <p className={styles.text}>Severity</p>
                        <Slider className={styles.slider} value={sliderValue} onChange={handleSliderChange} trackStyle={{backgroundColor:"#5C98D2"}} handleStyle={{backgroundColor:"#5C98D2"}} /> 
                    </div>
                    <div>
                        <p className={styles.text}>Duration</p>
                        <DropdownMenuComponent selected={selected} options={options} onClick={handleDropdownArrowClick} />
                    </div>
                </div>
                <Button text="CONTINUE" onClick={handleButtonClick} />
                <Disclaimer />
            </div>
        </>
    );
};

export default DiabetesPage;