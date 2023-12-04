import styles from '../styles/components/Button.module.css'

const Button = (props : {text : string, onClick : React.MouseEventHandler<HTMLDivElement>}) => {
    return (
        <>
            <div className={styles.container} onClick={props.onClick}>
                <p className={styles.text}>{props.text}</p>
            </div>
        </>
    );
};

export default Button;