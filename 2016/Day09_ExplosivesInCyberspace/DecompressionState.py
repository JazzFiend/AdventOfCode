from enum import Enum

class DecompressionState(Enum):
  NOT_COMPRESSED = 1
  EXTRACT_CHAR_LENGTH = 2
  EXTRACT_DUPLICATES = 3
  SKIP_DUPLICATES = 4