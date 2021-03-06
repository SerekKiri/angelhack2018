import ActionNode from '../components/nodes/ActionNode'
import TerminatorNode from '../components/nodes/TerminatorNode'
import DecisionNode from '../components/nodes/DecisionNode'

const sq2 = Math.sqrt(2)

const simpleActionConnectors = {
  in: {
    name: 'in',
    color: '#3498db',
    type: 'IN',
    x: 50,
    y: 0
  },
  out: {
    name: 'out',
    color: '#34495e',
    type: 'OUT',
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
    propertiesModel: [],
    connectors: {
      out: {
        color: '#3498db', // peter river
        name: 'out',
        type: 'OUT',
        x: 50,
        y: 30
      }
    }
  },
  END: {
    name: 'End',
    color: '#D84315',
    component: TerminatorNode,
    width: 100,
    height: 30,
    propertiesModel: [],
    connectors: {
      in: {
        color: '#3498db', // peter river
        name: 'in',
        type: 'IN',
        x: 50,
        y: 0
      }
    }
  },

  NOTIFICATION: {
    name: 'Notification',
    propertiesModel: [
      {
        type: String,
        key: 'title',
        label: 'Title'
      },
      {
        type: 'LONG_STRING',
        key: 'content',
        label: 'Content'
      }
    ],
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  /* APPROVAL: {
    name: 'Approval',
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },*/
  EDIT_REQUEST: {
    name: 'Edit Request',
    propertiesModel: [
      {
        type: String,
        key: 'title',
        label: 'Title'
      },
      {
        type: 'LONG_STRING',
        key: 'content',
        label: 'Content'
      }
    ],
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  WAIT: {
    name: 'Wait',
    propertiesModel: [
      {
        type: String,
        key: 'title',
        label: 'Title'
      },
      {
        type: 'LONG_STRING',
        key: 'content',
        label: 'Content'
      }
    ],
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  EMAIL: {
    name: 'Email',
    propertiesModel: [
      {
        type: String,
        key: 'title',
        label: 'Title'
      },
      {
        type: 'LONG_STRING',
        key: 'content',
        label: 'Content'
      }
    ],
    color: '#1976D2',
    component: ActionNode,
    width: 100,
    height: 30,
    connectors: simpleActionConnectors
  },
  APPROVAL: {
    name: 'Approval',
    component: DecisionNode,
    width: sq2 * 100,
    height: 100 * sq2,
    connectors: {
      in: {
        color: '#34495e', // dark blue
        name: 'in',
        type: 'IN',
        x: 50,
        y: 0
      },
      approved: {
        color: '#2ecc71', // green
        name: 'approved',
        type: 'OUT',
        x: -20,
        y: sq2 * 50
      },
      rejected: {
        color: '#e74c3c', // red
        name: 'rejected',
        type: 'OUT',
        x: 120,
        y: sq2 * 50
      }
    }
  }
}
