export interface Components {
  id: string;
  type: string;
  label: string;
  required: boolean;
  hint?: string;
  field?: Field[];
  ref?: Ref[];
  validation?: string,
  mask?: string,
  fileType?: {[key: string]: boolean};
  maxSize?: number;
  isHorizontal?: boolean;
  isVertical?: boolean;
}

export interface Field {
  label: string,
  value: string;
}

export interface Ref {
  relatedRel: string;
  val: string;
  relation: string;
}
