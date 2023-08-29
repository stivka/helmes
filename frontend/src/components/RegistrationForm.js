import React, { useState } from 'react';
import axios from 'axios';
import SectorDropdown from './SectorDropdown';

const RegistrationForm = () => {
    const [name, setName] = useState('');
    const [selectedSectors, setSelectedSectors] = useState([]);
    const [agreeToTerms, setAgreeToTerms] = useState(false);
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [formUuid, setFormUuid] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!name || selectedSectors.length === 0 || !agreeToTerms) {
            alert("All fields are mandatory!");
            return;
        }
        axios.post('http://localhost:8080/api/registration', {
            name,
            sectors: selectedSectors,
            agreeToTerms,
            uuid: formUuid ? formUuid : undefined
        }, {
            withCredentials: true
        })
            .then(response => {
                console.log('Data saved successfully:', response.data);
                setFormUuid(response.data);
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
                    <SectorDropdown selectedSectors={selectedSectors} onSectorsChange={setSelectedSectors} editable={!isSubmitted} />
                </div>
            </label>
            <br />
            <br />
            <label>
                <input type="checkbox" checked={agreeToTerms} onChange={() => setAgreeToTerms(!agreeToTerms)} disabled={isSubmitted} />
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
