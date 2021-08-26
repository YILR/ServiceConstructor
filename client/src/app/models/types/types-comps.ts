import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class TypesComps {
  customs: string[] = ['StringInput', 'DateInput', 'AddressInput','RadioInput',
    'CheckBox', 'TextArea', 'Lookup', 'DropDown', 'DocInput', 'LabelSection']

  types: any = {
    QUESTION: ['QuestionScr', 'InfoComponent'],
    CUSTOM: this.customs,
    UNIQUE: ['FileUploadComponent'],
    INFO: ['InfoScr', 'LabelSection', 'InfoComponent'],
    REPEATABLE: this.customs,
    CUNIQUE: this.customs,
    EMPTY: ['Redirect']
  };

  fileType = [
    "JPEG", "JPG", "PNG", "PDF", "RAR", "ZIP", "BMP", "TIFF", "SIG"
  ];


}
