import {Components} from "./Component";

export interface Screen {
  id: string;
  type: string;
  header: string;
  child: Child;
  components: Components[];
  screenRules: ScreenRule[];
  cycleScreenRules: ScreenRule[];
}

export interface Child {
  id: string;
  label: string;
  minAge: number;
  maxAge: number;
  singleChild: boolean;
  isCycled: boolean;
}

export interface ScreenRule {
  conditions: Condition[];
  nextDisplay: string;
}

export interface Condition {
  field: string;
  value: string;
  predicate: string
}
