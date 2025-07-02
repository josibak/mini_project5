
import { useState } from 'react';
import { ChevronDown, Edit, FileText, UserPlus } from 'lucide-react';
import { Link } from 'react-router-dom';

const AuthorDropdown = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div className="relative">
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="flex items-center gap-2 text-gray-700 hover:text-gray-900 transition-colors duration-200"
      >
        <span>AUTHOR</span>
        <ChevronDown size={16} />
      </button>

      {isOpen && (
        <div className="absolute right-0 mt-2 w-56 bg-white rounded-lg shadow-lg border border-warm-brown-200 py-2 z-50">
          <Link
            to="/my-books"
            className="flex items-center gap-3 px-4 py-3 text-gray-700 hover:bg-warm-brown-50 transition-colors"
            onClick={() => setIsOpen(false)}
          >
            <FileText className="w-4 h-4" />
            나의 글 관리
          </Link>
          <Link
            to="/author-submission"
            className="flex items-center gap-3 px-4 py-3 text-gray-700 hover:bg-warm-brown-50 transition-colors"
            onClick={() => setIsOpen(false)}
          >
            <Edit className="w-4 h-4" />
            새 글 작성하기
          </Link>
          <Link
            to="/author-registration"
            className="flex items-center gap-3 px-4 py-3 text-gray-700 hover:bg-warm-brown-50 transition-colors"
            onClick={() => setIsOpen(false)}
          >
            <UserPlus className="w-4 h-4" />
            작가 등록 신청
          </Link>
        </div>
      )}
    </div>
  );
};

export default AuthorDropdown;
