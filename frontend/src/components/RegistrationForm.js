import React, { useState } from 'react';
import axios from 'axios';
import SectorDropdown from './SectorDropdown';
import './RegistrationForm.css';

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
        <div className="container">
            <form onSubmit={handleSubmit} className="form">
                <fieldset className="fieldset">
                    <legend className="legend">Please enter your name and pick the sectors that you are currently involved in</legend>
                    <div className="form-group">
                        <label htmlFor="name" className="label">Name:</label>
                        <input type="text" id="name" className="input" value={name} onChange={e => setName(e.target.value)} disabled={isSubmitted} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="sector" className="label">Select a sector:</label>
                        <SectorDropdown selectedSectors={selectedSectors} onSectorsChange={setSelectedSectors} editable={!isSubmitted} />
                    </div>
                    <div className="form-group checkbox-group">
                        <input type="checkbox" id="agreeToTerms" className="input" checked={agreeToTerms} onChange={() => setAgreeToTerms(!agreeToTerms)} disabled={isSubmitted} />
                        <label className="checkbox-label" htmlFor="agreeToTerms">Agree to terms</label>
                    </div>
                    <div className="form-group">
                        {isSubmitted ? (
                            <button type="button" className="button" onClick={handleEdit}>Edit</button>
                        ) : (
                            <input type="submit" className="input" value="Save" />
                        )}
                    </div>
                </fieldset>
            </form>
        </div>
    );

};

export default RegistrationForm;
