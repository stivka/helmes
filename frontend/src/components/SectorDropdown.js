import React, { useState, useEffect } from 'react';
import axios from 'axios';

function SectorDropdown({ selectedSector, onSectorChange }) {
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
                    {getIndentation(sector.depthLevel)}{sector.name}
                </option>
            ))}
        </select>
    );
}

function getIndentation(level) {
    return '\u00A0'.repeat(level * 4); // 4 spaces for each level
}

export default SectorDropdown;