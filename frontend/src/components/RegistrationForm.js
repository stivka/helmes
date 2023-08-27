import React, { useState } from 'react';
import axios from 'axios';
import SectorDropdown from './SectorDropdown';

const RegistrationForm = () => {
    const [name, setName] = useState('');
    const [selectedSectors, setSelectedSectors] = useState('');
    const [agreedToTerms, setAgreedToTerms] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!name || !selectedSectors || !agreedToTerms) {
            alert("All fields are mandatory!");
            return;
        }
        axios.post('http://localhost:8080/api/registration', { name, sectors: selectedSectors, agreedToTerms }, { withCredentials: true })
            .then(response => {
                console.log('Data saved successfully:', response.data);
                setIsSubmitted(true);
            })
            .catch(error => {
                console.error('Error saving data:', error);
            });
    };

    const handleEdit = () => {
        setIsSubmitted(false);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                Please enter your name and pick the Sectors you are currently involved in.
            </div>
            <br />
            <label>
                Name:
                <input type="text" value={name} onChange={e => setName(e.target.value)} disabled={isSubmitted} />
            </label>
            <br />
            <br />
            <label>
                <div>
                    <h4>Select a Sector</h4>
                    <SectorDropdown selectedSectors={selectedSectors} onSectorsChange={setSelectedSectors} disabled={isSubmitted} />
                </div>
            </label>
            <br />
            <br />
            <label>
                <input type="checkbox" checked={agreedToTerms} onChange={() => setAgreedToTerms(!agreedToTerms)} disabled={isSubmitted} />
                Agree to terms
            </label>
            <br />
            <br />
            {isSubmitted ? (
                <button type="button" onClick={handleEdit}>Edit</button>
            ) : (
                <input type="submit" value="Save" />
            )}
        </form>
    );
};

export default RegistrationForm;
