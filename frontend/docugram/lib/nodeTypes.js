import ActionNode from '../components/nodes/ActionNode'
import EntryNode from '../components/nodes/EntryNode'
import DecisionNode from '../components/nodes/DecisionNode'

const sq2 = Math.sqrt(2)

export default {
  ENTRY: {
    name: 'Entry',
    component: EntryNode,
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
  action: {
    name: 'Action',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: {
      out: {
        color: '#3498db', // peter river
        type: 'OUT',
        x: 50,
        y: 0
      },
      in: {
        color: '#34495e', // dark blue
        type: 'IN',
        x: 50,
        y: 30
      }
    }
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
