import { Edges, Node, Nodes } from 'v-network-graph'
import { IdLabelProps } from '@/types'
import { NodePoint, TopologyGraphList, TopologyGraph } from '@/types/topology'
import { ViewType, DisplayType } from '@/components/topology/topology.constants'

export interface State {
  isTopologyView: boolean // switch between geo-map and topology
  selectedView: string // map, d3, circle layout etc.
  selectedDisplay: string // nodes, powergrid etc.
  edges: Edges
  vertices: Nodes
  semanticZoomLevel: number
  isLeftDrawerOpen: boolean
  isRightDrawerOpen: boolean
  focusObjects: IdLabelProps[]
  layout: Record<string, NodePoint>
  defaultObjects: Node[]
  highlightFocusedObjects: boolean
  modalState: boolean
  nodeIcons: Record<string, string>
  topologyGraphs: TopologyGraphList[]
  topologyGraphsDisplay: TopologyGraphList
  topologyGraphsSubLayers: TopologyGraph[]
  container: string
  namespace: string
  idsWithSubLayers: string[]
}

const state: State = {
  isTopologyView: false,
  selectedView: ViewType.map, // default layout
  selectedDisplay: DisplayType.nodes, // default graph
  edges: {},
  vertices: {},
  semanticZoomLevel: 1,
  isLeftDrawerOpen: true,
  isRightDrawerOpen: false,
  focusObjects: [],
  layout: {},
  defaultObjects: [],
  highlightFocusedObjects: false,
  modalState: false,
  nodeIcons: {},
  topologyGraphs: [],
  topologyGraphsDisplay: {},
  topologyGraphsSubLayers: [],
  container: '',
  namespace: '',
  idsWithSubLayers: []
}

export default state
