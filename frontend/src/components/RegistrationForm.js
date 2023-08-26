import React, { useState } from 'react';
import axios from 'axios';
import SectorDropdown from './SectorDropdown';

const RegistrationForm = () => {
    const [name, setName] = useState('');
    const [selectedSector, setSelectedSector] = useState('');
    const [agreedToTerms, setAgreedToTerms] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!name || !selectedSector || !agreedToTerms) {
            alert("All fields are mandatory!");
            return;
        }
        axios.post('http://localhost:8080/users', { name, sectors: selectedSector, agreedToTerms })
            .then(response => {
                console.log('Data saved successfully:', response.data);
            })
            .catch(error => {
                console.error('Error saving data:', error);
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                Please enter your name and pick the Sectors you are currently involved in.
            </div>
            <br />
            <label>
                Name:
                <input type="text" value={name} onChange={e => setName(e.target.value)} />
            </label>
            <br />
            <br />
            <label>
                <div>
                    <h4>Select a Sector</h4>
                    <SectorDropdown selectedSector={selectedSector} onSectorChange={setSelectedSector} />
                </div>
            </label>
            <br />
            <br />
            <label>
                <input type="checkbox" checked={agreedToTerms} onChange={() => setAgreedToTerms(!agreedToTerms)} />
                Agree to terms
            </label>
            <br />
            <br />
            <input type="submit" value="Save" />
        </form>
    );
};

export default RegistrationForm;
