import styles from '../styles/components/CheckBoxGroupComponent.module.css'

const CheckBoxGroupComponent = (props : {data : Record<string, boolean>, onChange : (symptom: string) => void}) => {
    return (
        <>
            <div className={styles.container}>
                {
                    Object.entries(props.data).map(([key, value]) => {
                        return (
                            <div className={styles.group}>   
                                <input type='checkbox' checked={value} className={styles.box} onChange={() => props.onChange(key)} />{key}
                            </div>
                        );
                    })
                }
            </div>
        </>
    );
};

export default CheckBoxGroupComponent;