import React from 'react';
import { useParams } from 'react-router-dom';
import data from '../dummy/data';

const Detail = () => {
    const { id } = useParams();
    const item = data.find(item => item.id == id);


    return (
        <div>
            url : {item.id}<br />
            {item.title}<br />
            {item.content}<br />
            {item.period}
        </div>
    );
};

export default Detail;
