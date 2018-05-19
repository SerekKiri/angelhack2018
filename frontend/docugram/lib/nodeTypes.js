import ActionNode from '../components/nodes/ActionNode'
import TerminatorNode from '../components/nodes/TerminatorNode'
import DecisionNode from '../components/nodes/DecisionNode'

const sq2 = Math.sqrt(2)

const simpleActionConnectors = {
  out: {
    color: '#3498db',
    type: 'OUT',
    x: 50,
    y: 0
  },
  in: {
    color: '#34495e',
    type: 'IN',
    x: 50,
    y: 30
  }
}
export default {
  ENTRY: {
    name: 'Entry',
    color: '#4CAF50',
    component: TerminatorNode,
    width: 100,
    height: 30,
    connectors: {
      out: {
        color: '#3498db', // peter river
        type: 'OUT',
        x: 50,
        y: 0
      }
    }
  },
  NOTIFICATION: {
    name: 'Notification',
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  APPROVAL: {
    name: 'Approval',
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  EDIT_REQUEST: {
    name: 'Edit Request',
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  WAIT: {
    name: 'Wait',
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  EMAIL: {
    name: 'Email',
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  decision: {
    name: 'Decision',
    component: DecisionNode,
    width: sq2 * 100,
    height: 100 * sq2,
    connectors: {
      in: {
        color: '#34495e', // dark blue
        type: 'IN',
        x: 50,
        y: sq2 * 100
      },
      approved: {
        color: '#2ecc71', // green
        type: 'OUT',
        x: -20,
        y: sq2 * 50
      },
      rejected: {
        color: '#e74c3c', // red
        type: 'OUT',
        x: 120,
        y: sq2 * 50
      }
    }
  }
}
