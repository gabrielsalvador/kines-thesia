
import React, { useEffect } from 'react';
import TopBar from './layout/TopBar';
import Canvas from './components/Canvas';
import PianoRoll from './components/PianoRoll';
import Terminal from './components/Terminal';


const App = () => {



return (<div style={
  {
    display: 'grid',
    gridTemplateRows: 'auto 1fr 200px',
    gridTemplateColumns: 'auto 1fr auto',
    width: '100vw',
    height: '100vh',
    gap: '3px',
  }
}>
  <div style={{ gridRow: '1', gridColumn: '2' }}><TopBar></TopBar></div>
  <div style={{ gridRow: '2', gridColumn: '1' }}>Left Panel</div>
  <div style={{ gridRow: '2', gridColumn: '2' }}><Canvas></Canvas></div>
  <div style={{ gridRow: '2', gridColumn: '3' }}>Right Panel</div>
  <div style={{ gridRow: '3', gridColumn: '1 / -1', display: "flex", gap: "3px" }}>
    <div style={{ flex: 1 }}>
      <div>

      </div>
    </div>
    <div style={{ flex: 1 }}>
      <Terminal></Terminal>
    </div>
  </div>
</div>)

};

export default App;


