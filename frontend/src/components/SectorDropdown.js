import React, { useEffect, useState } from 'react';
import axios from 'axios';
import TreeSelect, { SHOW_PARENT } from 'rc-tree-select';
import 'rc-tree-select/assets/index.less';

function SectorDropdown({ selectedSectors, onSectorsChange, editable }) {
    const [treeData, setTreeData] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/sectors')
            .then(response => {
                const sectors = response.data;
                const treeData = getTreeData(sectors);
                setTreeData(treeData);
            })
            .catch(error => {
                console.error("There was an error fetching sectors:", error);
            });
    }, []);

    return (
        <TreeSelect
            treeData={treeData}
            value={selectedSectors}
            onChange={onSectorsChange}
            treeCheckable={true}
            showCheckedStrategy={SHOW_PARENT}
            searchPlaceholder="Please select"
            // doesn't support all styles thru the prop
            style={{ width: 500 }}
            disabled={!editable}
        />
    );
}

function getTreeData(sectors) {
    const treeData = [];

    // Function to recursively build treeData for a given parent
    const buildTreeData = (parent) => {
        const children = sectors.filter(sector => sector.parent && sector.parent.id === parent.id);
        if (children.length === 0) {
            return {
                value: parent.id.toString(),
                label: parent.name,
            };
        }
        const childrenTreeData = children.map(buildTreeData);
        return {
            value: parent.id.toString(),
            label: parent.name,
            children: childrenTreeData,
        };
    };

    // Build treeData for all root level sectors
    const rootSectors = sectors.filter(sector => !sector.parent);
    rootSectors.forEach(rootSector => {
        treeData.push(buildTreeData(rootSector));
    });

    return treeData;
}

export default SectorDropdown;
