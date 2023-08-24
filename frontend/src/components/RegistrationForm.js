import React, { useState } from 'react';
import SectorDropdown from './SectorDropdown';

const RegistrationForm = () => {
    const [name, setName] = useState('');
    const [selectedSector, setSelectedSector] = useState('');
    const [agreedToTerms, setAgreedToTerms] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(name, selectedSectors, agreedToTerms);
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
                    <h1>Select a Sector</h1>
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
