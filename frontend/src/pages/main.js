import styles from '../css/main.module.scss';
import data from '../dummy/data'
import { useNavigate } from 'react-router-dom';

function Main() {
    const navigate = useNavigate()
    return (
        <div className={styles.main_container}>
            {data.map((a, i) => (
                <div className={styles.main_item}>
                    <img src="logo.png" onClick={() => navigate('/detail/' + i)} />
                    <div className={styles.main_info}>
                        <h4>{a.id}</h4>
                        <span>{a.title}</span>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default Main
