import React from "react";
import ArrowDropDown from "@material-ui/icons/ArrowDropDown";
import styles from '../styles/components/DropdownMenuComponent.module.css';

const DropdownMenuComponent = (props : {selected : string, options : string[], onClick : (selected: string) => void}) => {
    const [clicked, setClicked] = React.useState<boolean>(false);

    const handleArrowClick = () => {
        setClicked(!clicked);
    }

    return (
        <>
            <div className={styles.menu}>
                <div className={styles.selectionBox}>
                    <p className={styles.selection}>{props.selected}</p>
                    <div className={styles.icon}>
                        <ArrowDropDown onClick={handleArrowClick} />
                    </div>
                </div>
                {
                    clicked &&
                    ( 
                        <div className={styles.options}>
                            {
                                props.options.map((value, key) => {
                                    return (
                                        <a className={styles.option} onClick={() => props.onClick(value)}>
                                            <p>{value}</p>
                                        </a>
                                    );
                                })
                            }
                        </div>
                    )
                }
            </div>
        </>
    );
};

export default DropdownMenuComponent;