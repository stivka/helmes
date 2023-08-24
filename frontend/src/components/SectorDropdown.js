import React, { useState, useEffect } from 'react';
import axios from 'axios';

function SectorDropdown() {
    const [sectors, setSectors] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/sectors')
            .then(response => {
                setSectors(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching sectors:", error);
            });
    }, []);

    return (
        <select value={selectedSector} onChange={e => onSectorChange(e.target.value)}>
            {sectors.map(sector => (
                <option key={sector.id} value={sector.id}>
                    {sector.name}
                </option>
            ))}
        </select>
    );
}

export default SectorDropdown;