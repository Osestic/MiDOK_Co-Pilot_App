import React from "react";
import styles from '../styles/components/SearchBarComponent.module.css';
import SearchIcon from "@material-ui/icons/Search";
import CloseIcon from "@material-ui/icons/Close";

const SearchBarComponent = (props : {placeholder : string, data : string[], onClick : React.MouseEventHandler<HTMLAnchorElement>}) => {
    const [filteredData, setFilteredData] = React.useState<string[]>([]);
    const [typedWord, setTypedWord] = React.useState<string>("");


    const handleFilter = (event: React.ChangeEvent<HTMLInputElement>) => {
        const wordEntered = event.target.value;
        setTypedWord(wordEntered);
        const newFilter = props.data.filter((value) => {
            return value.toLowerCase().includes(wordEntered.toLowerCase());
        });
        if (wordEntered === "") {
            setFilteredData([]);
        } else {
            setFilteredData(newFilter);
        }
    }

    const clearSearchBar = () => {
        setFilteredData([]);
        setTypedWord("");
    };

    return (
        <div className={styles.search}>
            <div className={styles.searchInputs}>
                <input className={styles.input} type="text" value={typedWord} placeholder={props.placeholder} onChange={handleFilter} />
                <div className={styles.searchIcon}>
                    {filteredData.length !== 0 ? <CloseIcon className={styles.clearBtn} onClick={clearSearchBar} /> : <SearchIcon />}
                </div>
            </div>
            {filteredData.length != 0 && 
                (
                    <div className={styles.dataResult}>
                        {filteredData.map((value, key) => {
                            return (
                                <a className={styles.dataItem} onClick={props.onClick}>
                                    <p>{value}</p>
                                </a>
                            );
                        })}
                    </div>
                )
            }
        </div>
    );
};

export default SearchBarComponent;