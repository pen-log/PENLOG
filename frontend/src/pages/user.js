import { useParams } from 'react-router-dom';
import data from '../dummy/data';

const User = () => {
    const { id } = useParams();
    // const item = data.find(item => item.id == id);
    // console.log(item)

    return (
        <div>
            {id}님 페이지입니다.
            글목록 내목록 어쩌구저쩌구~
        </div>
    );
};

export default User;
