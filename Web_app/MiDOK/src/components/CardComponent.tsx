import styles from '../styles/components/CardComponent.module.css'

const CardComponent = (props : {text : string, onClick : React.MouseEventHandler<HTMLDivElement>}) => {
    return (
        <>
            <div className={styles.container} onClick={props.onClick}>
                <p className={styles.text}>{props.text}</p>
            </div>
        </>
    );
};

export default CardComponent;